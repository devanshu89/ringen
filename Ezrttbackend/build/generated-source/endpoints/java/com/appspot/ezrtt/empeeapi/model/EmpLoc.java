/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2016-02-18 22:11:37 UTC)
 * on 2016-03-05 at 22:59:28 UTC 
 * Modify at your own risk.
 */

package com.appspot.ezrtt.empeeapi.model;

/**
 * Model definition for EmpLoc.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the empeeapi. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class EmpLoc extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String empNum;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String locdate;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private GeoPt ltln;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getEmpNum() {
    return empNum;
  }

  /**
   * @param empNum empNum or {@code null} for none
   */
  public EmpLoc setEmpNum(java.lang.String empNum) {
    this.empNum = empNum;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLocdate() {
    return locdate;
  }

  /**
   * @param locdate locdate or {@code null} for none
   */
  public EmpLoc setLocdate(java.lang.String locdate) {
    this.locdate = locdate;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public GeoPt getLtln() {
    return ltln;
  }

  /**
   * @param ltln ltln or {@code null} for none
   */
  public EmpLoc setLtln(GeoPt ltln) {
    this.ltln = ltln;
    return this;
  }

  @Override
  public EmpLoc set(String fieldName, Object value) {
    return (EmpLoc) super.set(fieldName, value);
  }

  @Override
  public EmpLoc clone() {
    return (EmpLoc) super.clone();
  }

}
