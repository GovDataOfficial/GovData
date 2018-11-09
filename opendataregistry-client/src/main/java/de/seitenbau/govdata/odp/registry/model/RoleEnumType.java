/**
 * Copyright (c) 2012, 2013 Fraunhofer Institute FOKUS | 2017 SEITENBAU GmbH
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

package de.seitenbau.govdata.odp.registry.model;

import de.seitenbau.govdata.odp.registry.model.exception.UnknownRoleException;
import lombok.Getter;

/**
 * The Enum RoleEnumType.
 *
 * @author rnoerenberg
 * @author sim, msg
 */
public enum RoleEnumType
{

  /** Der Autor. */
  CREATOR("author", "Autor"),

  /** Der Herausgeber. */
  PUBLISHER("publisher", "Herausgeber"),

  /** Der Ansprechpartner. */
  MAINTAINER("maintainer", "Ansprechpartner"),

  /** Der Bearbeiter. */
  CONTRIBUTOR("contributor", "Bearbeiter"),

  /** Der Urheber. */
  ORIGINATOR("originator", "Urheber");

  @Getter
  private String field;

  @Getter
  private String displayName;

  private RoleEnumType(String field, String displayName)
  {
    this.field = field;
    this.displayName = displayName;
  }

  /**
   * Get @link RoleEnumType from field name.
   * 
   * @param field name of the field
   * @return the corresponding {@link RoleEnumType} to the <b>field</b>
   * @throws UnknownRoleException if the given field name does not match to any {@link RoleEnumType}
   */
  public static RoleEnumType fromField(String field) throws UnknownRoleException
  {
    for (RoleEnumType role : RoleEnumType.values())
    {
      if (role.getField().equals(field))
      {
        return role;
      }
    }

    throw new UnknownRoleException("Role with the fieldname '" + field + "' is not known.");
  }
}
