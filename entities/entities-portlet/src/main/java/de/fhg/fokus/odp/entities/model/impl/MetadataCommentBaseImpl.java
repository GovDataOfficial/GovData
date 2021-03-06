package de.fhg.fokus.odp.entities.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import de.fhg.fokus.odp.entities.model.MetadataComment;
import de.fhg.fokus.odp.entities.service.MetadataCommentLocalServiceUtil;

/**
 * The extended model base implementation for the MetadataComment service. Represents a row in the &quot;entities_MetadataComment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MetadataCommentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MetadataCommentImpl
 * @see de.fhg.fokus.odp.entities.model.MetadataComment
 * @generated
 */
public abstract class MetadataCommentBaseImpl extends MetadataCommentModelImpl
    implements MetadataComment {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a metadata comment model instance should use the {@link MetadataComment} interface instead.
     */
    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            MetadataCommentLocalServiceUtil.addMetadataComment(this);
        } else {
            MetadataCommentLocalServiceUtil.updateMetadataComment(this);
        }
    }
}
