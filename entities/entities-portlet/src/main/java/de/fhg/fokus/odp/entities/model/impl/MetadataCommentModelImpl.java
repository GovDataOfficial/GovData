package de.fhg.fokus.odp.entities.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import de.fhg.fokus.odp.entities.model.MetadataComment;
import de.fhg.fokus.odp.entities.model.MetadataCommentModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the MetadataComment service. Represents a row in the &quot;entities_MetadataComment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link de.fhg.fokus.odp.entities.model.MetadataCommentModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link MetadataCommentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MetadataCommentImpl
 * @see de.fhg.fokus.odp.entities.model.MetadataComment
 * @see de.fhg.fokus.odp.entities.model.MetadataCommentModel
 * @generated
 */
public class MetadataCommentModelImpl extends BaseModelImpl<MetadataComment>
    implements MetadataCommentModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. All methods that expect a metadata comment model instance should use the {@link de.fhg.fokus.odp.entities.model.MetadataComment} interface instead.
     */
    public static final String TABLE_NAME = "entities_MetadataComment";
    public static final Object[][] TABLE_COLUMNS = {
            { "uuid_", Types.VARCHAR },
            { "_id", Types.BIGINT },
            { "userLiferayId", Types.BIGINT },
            { "metadataName", Types.VARCHAR },
            { "text_", Types.VARCHAR },
            { "created", Types.TIMESTAMP }
        };
    public static final String TABLE_SQL_CREATE = "create table entities_MetadataComment (uuid_ VARCHAR(75) null,_id LONG not null primary key,userLiferayId LONG,metadataName VARCHAR(75) null,text_ VARCHAR(75) null,created DATE null)";
    public static final String TABLE_SQL_DROP = "drop table entities_MetadataComment";
    public static final String ORDER_BY_JPQL = " ORDER BY metadataComment.created ASC";
    public static final String ORDER_BY_SQL = " ORDER BY entities_MetadataComment.created ASC";
    public static final String DATA_SOURCE = "liferayDataSource";
    public static final String SESSION_FACTORY = "liferaySessionFactory";
    public static final String TX_MANAGER = "liferayTransactionManager";
    public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.entity.cache.enabled.de.fhg.fokus.odp.entities.model.MetadataComment"),
            true);
    public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.finder.cache.enabled.de.fhg.fokus.odp.entities.model.MetadataComment"),
            true);
    public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
                "value.object.column.bitmask.enabled.de.fhg.fokus.odp.entities.model.MetadataComment"),
            true);
    public static long METADATANAME_COLUMN_BITMASK = 1L;
    public static long USERLIFERAYID_COLUMN_BITMASK = 2L;
    public static long UUID_COLUMN_BITMASK = 4L;
    public static long CREATED_COLUMN_BITMASK = 8L;
    public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
                "lock.expiration.time.de.fhg.fokus.odp.entities.model.MetadataComment"));
    private static ClassLoader _classLoader = MetadataComment.class.getClassLoader();
    private static Class<?>[] _escapedModelInterfaces = new Class[] {
            MetadataComment.class
        };
    private String _uuid;
    private String _originalUuid;
    private long __id;
    private long _userLiferayId;
    private long _originalUserLiferayId;
    private boolean _setOriginalUserLiferayId;
    private String _metadataName;
    private String _originalMetadataName;
    private String _text;
    private Date _created;
    private long _columnBitmask;
    private MetadataComment _escapedModel;

    public MetadataCommentModelImpl() {
    }

    @Override
    public long getPrimaryKey() {
        return __id;
    }

    @Override
    public void setPrimaryKey(long primaryKey) {
        set_id(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return __id;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    @Override
    public Class<?> getModelClass() {
        return MetadataComment.class;
    }

    @Override
    public String getModelClassName() {
        return MetadataComment.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("uuid", getUuid());
        attributes.put("_id", get_id());
        attributes.put("userLiferayId", getUserLiferayId());
        attributes.put("metadataName", getMetadataName());
        attributes.put("text", getText());
        attributes.put("created", getCreated());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String uuid = (String) attributes.get("uuid");

        if (uuid != null) {
            setUuid(uuid);
        }

        Long _id = (Long) attributes.get("_id");

        if (_id != null) {
            set_id(_id);
        }

        Long userLiferayId = (Long) attributes.get("userLiferayId");

        if (userLiferayId != null) {
            setUserLiferayId(userLiferayId);
        }

        String metadataName = (String) attributes.get("metadataName");

        if (metadataName != null) {
            setMetadataName(metadataName);
        }

        String text = (String) attributes.get("text");

        if (text != null) {
            setText(text);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }
    }

    @Override
    public String getUuid() {
        if (_uuid == null) {
            return StringPool.BLANK;
        } else {
            return _uuid;
        }
    }

    @Override
    public void setUuid(String uuid) {
        if (_originalUuid == null) {
            _originalUuid = _uuid;
        }

        _uuid = uuid;
    }

    public String getOriginalUuid() {
        return GetterUtil.getString(_originalUuid);
    }

    @Override
    public long get_id() {
        return __id;
    }

    @Override
    public void set_id(long _id) {
        __id = _id;
    }

    @Override
    public long getUserLiferayId() {
        return _userLiferayId;
    }

    @Override
    public void setUserLiferayId(long userLiferayId) {
        _columnBitmask |= USERLIFERAYID_COLUMN_BITMASK;

        if (!_setOriginalUserLiferayId) {
            _setOriginalUserLiferayId = true;

            _originalUserLiferayId = _userLiferayId;
        }

        _userLiferayId = userLiferayId;
    }

    public long getOriginalUserLiferayId() {
        return _originalUserLiferayId;
    }

    @Override
    public String getMetadataName() {
        if (_metadataName == null) {
            return StringPool.BLANK;
        } else {
            return _metadataName;
        }
    }

    @Override
    public void setMetadataName(String metadataName) {
        _columnBitmask |= METADATANAME_COLUMN_BITMASK;

        if (_originalMetadataName == null) {
            _originalMetadataName = _metadataName;
        }

        _metadataName = metadataName;
    }

    public String getOriginalMetadataName() {
        return GetterUtil.getString(_originalMetadataName);
    }

    @Override
    public String getText() {
        if (_text == null) {
            return StringPool.BLANK;
        } else {
            return _text;
        }
    }

    @Override
    public void setText(String text) {
        _text = text;
    }

    @Override
    public Date getCreated() {
        return _created;
    }

    @Override
    public void setCreated(Date created) {
        _columnBitmask = -1L;

        _created = created;
    }

    public long getColumnBitmask() {
        return _columnBitmask;
    }

    @Override
    public ExpandoBridge getExpandoBridge() {
        return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
            MetadataComment.class.getName(), getPrimaryKey());
    }

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
        ExpandoBridge expandoBridge = getExpandoBridge();

        expandoBridge.setAttributes(serviceContext);
    }

    @Override
    public MetadataComment toEscapedModel() {
        if (_escapedModel == null) {
            _escapedModel = (MetadataComment) ProxyUtil.newProxyInstance(_classLoader,
                    _escapedModelInterfaces, new AutoEscapeBeanHandler(this));
        }

        return _escapedModel;
    }

    @Override
    public Object clone() {
        MetadataCommentImpl metadataCommentImpl = new MetadataCommentImpl();

        metadataCommentImpl.setUuid(getUuid());
        metadataCommentImpl.set_id(get_id());
        metadataCommentImpl.setUserLiferayId(getUserLiferayId());
        metadataCommentImpl.setMetadataName(getMetadataName());
        metadataCommentImpl.setText(getText());
        metadataCommentImpl.setCreated(getCreated());

        metadataCommentImpl.resetOriginalValues();

        return metadataCommentImpl;
    }

    @Override
    public int compareTo(MetadataComment metadataComment) {
        int value = 0;

        value = DateUtil.compareTo(getCreated(), metadataComment.getCreated());

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MetadataComment)) {
            return false;
        }

        MetadataComment metadataComment = (MetadataComment) obj;

        long primaryKey = metadataComment.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public void resetOriginalValues() {
        MetadataCommentModelImpl metadataCommentModelImpl = this;

        metadataCommentModelImpl._originalUuid = metadataCommentModelImpl._uuid;

        metadataCommentModelImpl._originalUserLiferayId = metadataCommentModelImpl._userLiferayId;

        metadataCommentModelImpl._setOriginalUserLiferayId = false;

        metadataCommentModelImpl._originalMetadataName = metadataCommentModelImpl._metadataName;

        metadataCommentModelImpl._columnBitmask = 0;
    }

    @Override
    public CacheModel<MetadataComment> toCacheModel() {
        MetadataCommentCacheModel metadataCommentCacheModel = new MetadataCommentCacheModel();

        metadataCommentCacheModel.uuid = getUuid();

        String uuid = metadataCommentCacheModel.uuid;

        if ((uuid != null) && (uuid.length() == 0)) {
            metadataCommentCacheModel.uuid = null;
        }

        metadataCommentCacheModel._id = get_id();

        metadataCommentCacheModel.userLiferayId = getUserLiferayId();

        metadataCommentCacheModel.metadataName = getMetadataName();

        String metadataName = metadataCommentCacheModel.metadataName;

        if ((metadataName != null) && (metadataName.length() == 0)) {
            metadataCommentCacheModel.metadataName = null;
        }

        metadataCommentCacheModel.text = getText();

        String text = metadataCommentCacheModel.text;

        if ((text != null) && (text.length() == 0)) {
            metadataCommentCacheModel.text = null;
        }

        Date created = getCreated();

        if (created != null) {
            metadataCommentCacheModel.created = created.getTime();
        } else {
            metadataCommentCacheModel.created = Long.MIN_VALUE;
        }

        return metadataCommentCacheModel;
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{uuid=");
        sb.append(getUuid());
        sb.append(", _id=");
        sb.append(get_id());
        sb.append(", userLiferayId=");
        sb.append(getUserLiferayId());
        sb.append(", metadataName=");
        sb.append(getMetadataName());
        sb.append(", text=");
        sb.append(getText());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(22);

        sb.append("<model><model-name>");
        sb.append("de.fhg.fokus.odp.entities.model.MetadataComment");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>uuid</column-name><column-value><![CDATA[");
        sb.append(getUuid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>_id</column-name><column-value><![CDATA[");
        sb.append(get_id());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userLiferayId</column-name><column-value><![CDATA[");
        sb.append(getUserLiferayId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>metadataName</column-name><column-value><![CDATA[");
        sb.append(getMetadataName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>text</column-name><column-value><![CDATA[");
        sb.append(getText());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
