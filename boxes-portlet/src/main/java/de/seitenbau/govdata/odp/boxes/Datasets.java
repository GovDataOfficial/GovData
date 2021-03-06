/**
 * Copyright (c) 2015, 2017 SEITENBAU GmbH
 * <p>
 * This file is part of Open Data Platform.
 * <p>
 * Open Data Platform is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Affero General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 * <p>
 * Open Data Plaform is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License along with Open Data
 * Platform. If not, see <http://www.gnu.org/licenses/agpl-3.0>.
 */

package de.seitenbau.govdata.odp.boxes;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;

import de.seitenbau.govdata.cache.BaseCache;
import de.seitenbau.govdata.filter.SearchConsts;
import de.seitenbau.govdata.odp.boxes.model.DatasetModel;
import de.seitenbau.govdata.search.adapter.SearchService;
import de.seitenbau.govdata.search.common.SearchFilterBundle;
import de.seitenbau.govdata.search.gui.mapper.SearchResultsViewMapper;
import de.seitenbau.govdata.search.gui.model.HitViewModel;
import de.seitenbau.govdata.search.gui.model.LicenseViewModel;
import de.seitenbau.govdata.search.index.model.HitDto;
import de.seitenbau.govdata.search.index.model.SearchResultContainer;
import de.seitenbau.govdata.search.sort.Sort;
import de.seitenbau.govdata.search.sort.SortType;

/**
 * The class constitutes a bean that serves as a source for the latest datasets on the start page
 * boxes.
 *
 * @author rnoerenberg
 *
 */
@Component
@Scope("request")
public class Datasets implements Serializable
{
  private static final long serialVersionUID = 2779218730850295343L;

  /** The cache datasets key. */
  private final String CACHE_DATASETS_KEY = "datasets";

  /** The log. */
  private static final Logger LOG = LoggerFactory.getLogger(Datasets.class);

  /** The maximum number of the latest datasets to show. */
  private final int maximumNumberOfDatasets = 4;

  @Inject
  private SearchService searchService;

  @Inject
  private SearchResultsViewMapper searchResultsMapper;

  /** The datasets. */
  @Getter
  @Setter
  private List<DatasetModel> datasets;

  /**
   * An init method for the bean.
   */
  @SuppressWarnings("unchecked")
  @PostConstruct
  public void init()
  {
    datasets = (List<DatasetModel>) MultiVMPoolUtil.getCache(BaseCache.CACHE_NAME_BOXES)
        .get(CACHE_DATASETS_KEY);

    if (datasets == null)
    {
      LOG.info("Empty {} cache, fetching datasets from search index.", CACHE_DATASETS_KEY);
      datasets = getLatestDatasets(maximumNumberOfDatasets);
      if (CollectionUtils.isNotEmpty(datasets))
      {
        // safe cast: LinkedList
        MultiVMPoolUtil.getCache(BaseCache.CACHE_NAME_BOXES).put(CACHE_DATASETS_KEY, (Serializable) datasets);
      }
    }
  }

  private List<DatasetModel> getLatestDatasets(int maximumNumberOfDatasets)
  {
    Sort sorting = new Sort(SortType.LASTMODIFICATION, false);
    SearchFilterBundle searchFilterBundle = new SearchFilterBundle();
    searchFilterBundle.setTypeFilter(SearchConsts.TYPE_DATASET);
    SearchResultContainer searchResult =
        searchService.search("", maximumNumberOfDatasets, searchFilterBundle, sorting);
    List<HitDto> hits = searchResult.getHits();
    if (hits != null)
    {
      return hits.stream().map(this::toModel).filter(m -> Objects.nonNull(m)).collect(Collectors.toList());
    }

    return Collections.emptyList();
  }

  private DatasetModel toModel(HitDto hit)
  {
    HitViewModel hitViewModel;
    try
    {
      hitViewModel = searchResultsMapper.mapHitDtoToHitsViewModel(hit, null, null, false);
    }
    catch (Exception e)
    {
      return null;
    }

    DatasetModel datasetModel = new DatasetModel();
    datasetModel.setHit(hitViewModel);

    if (hitViewModel.getResourcesLicenses().size() > 1)
    {
      datasetModel.setMultipleLicenses(true);
    }
    else
    {
      Optional<LicenseViewModel> licenceOptional = hitViewModel.getResourcesLicenses().stream().findFirst();
      if (licenceOptional.isPresent())
      {
        datasetModel.setLicenseInfoText(licenceOptional.get().getTitle());
      }
    }

    return datasetModel;
  }

  /**
   * Gibt die maximale Anzahl an Einträgen für die Anzeige in der ersten Spalte zurück.
   * 
   * @return die maximale Anzahl an Einträgen für die erste Spalte.
   */
  public int getFirstColumnLength()
  {
    List<DatasetModel> datasets = this.getDatasets();
    return Math.min(datasets.size(), 2);
  }
}
