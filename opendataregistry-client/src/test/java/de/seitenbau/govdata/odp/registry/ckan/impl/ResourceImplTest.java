package de.seitenbau.govdata.odp.registry.ckan.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.seitenbau.govdata.odp.registry.ODRClient;
import de.seitenbau.govdata.odp.registry.ckan.json.LicenceBean;
import de.seitenbau.govdata.odp.registry.ckan.json.ResourceBean;
import de.seitenbau.govdata.odp.registry.model.Licence;

@RunWith(MockitoJUnitRunner.class)
public class ResourceImplTest
{
  private static final String LICENSE_UNKNOWN = "http://unknown-license";

  private static final String LICENSE_OPEN = "http://dcat-ap.de/def/licenses/dl-by-de/2.0";

  private static final String LICENSE_CLOSED = "http://dcat-ap.de/def/licenses/other-closed";

  @Mock
  private ODRClient odrClient;

  @Before
  public void setup() throws Exception
  {
    // Setup odrClient Mock
    expectListLicences();
  }

  @Test
  public void getLicence_known_licence_open() throws IOException, ParseException
  {
    /* prepare */
    ResourceImpl target = createDefaultResource(true);

    /* execute */
    Licence result = target.getLicense();

    /* verify */
    assertThat(result).isNotNull();
    assertThat(result.isOpen()).isTrue();
  }

  @Test
  public void getLicence_known_licence_closed() throws IOException, ParseException
  {
    /* prepare */
    ResourceImpl target = createDefaultResource(false);

    /* execute */
    Licence result = target.getLicense();

    /* verify */
    assertThat(result).isNotNull();
    assertThat(result.isOpen()).isFalse();
  }

  @Test
  public void getLicence_with_unknown_licenceId() throws IOException, ParseException
  {
    /* prepare */
    ResourceImpl target = createDefaultResource(LICENSE_UNKNOWN);

    /* execute */
    Licence result = target.getLicense();

    /* verify */
    assertThat(result).isNotNull();
    assertThat(result.isOpen()).isFalse();
  }

  @Test
  public void getLicence_with_licenceId_null() throws IOException, ParseException
  {
    /* prepare */
    ResourceImpl target = createDefaultResource(null);

    /* execute */
    Licence result = target.getLicense();

    /* verify */
    assertThat(result).isNotNull();
    assertThat(result.getName()).isEqualTo("license-id-not-set");
    assertThat(result.isOpen()).isFalse();
  }

  @Test
  public void isOpen_with_unknown_licence() throws IOException, ParseException
  {
    /* prepare */
    ResourceImpl target = createDefaultResource(LICENSE_UNKNOWN);

    /* execute */
    boolean result = target.isOpen();

    /* verify */
    assertThat(result).isFalse();
  }

  @Test
  public void isOpen_with_no_licence() throws IOException, ParseException
  {
    /* prepare */
    ResourceImpl target = createDefaultResource(null);

    /* execute */
    boolean result = target.isOpen();

    /* verify */
    assertThat(result).isFalse();
  }

  private ResourceImpl createDefaultResource(String licenseId)
  {
    return new ResourceImpl(this.odrClient, createDefaultResourceBean(licenseId));
  }

  private ResourceImpl createDefaultResource(boolean isOpen)
  {
    return new ResourceImpl(this.odrClient, createDefaultResourceBean(isOpen));
  }

  private ResourceBean createDefaultResourceBean(String license)
  {
    ResourceBean bean = new ResourceBean();
    bean.setLicense(license);
    return bean;
  }

  private ResourceBean createDefaultResourceBean(boolean isOpen)
  {
    if (isOpen)
    {
      return createDefaultResourceBean(LICENSE_OPEN);
    }
    else
    {
      return createDefaultResourceBean(LICENSE_CLOSED);
    }
  }

  private void expectListLicences()
  {
    List<Licence> licenceList = new ArrayList<>();
    LicenceBean licenceBeanOpen = createLicense(true, LICENSE_OPEN);
    LicenceBean licenceBeanClosed = createLicense(false, LICENSE_CLOSED);
    licenceList.add(new LicenceImpl(licenceBeanOpen));
    licenceList.add(new LicenceImpl(licenceBeanClosed));
    when(odrClient.listLicenses()).thenReturn(licenceList);
  }

  private LicenceBean createLicense(boolean isOpen, String id)
  {
    LicenceBean licenceBean = new LicenceBean();
    licenceBean.setId(id);
    licenceBean.set_okd_compliant(isOpen);
    return licenceBean;
  }
}
