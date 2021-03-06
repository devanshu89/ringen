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
 * Model definition for EmpDayInfo.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the empeeapi. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class EmpDayInfo extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer dayOfWeek;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<EmpHourInfo> hourInfo;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getDayOfWeek() {
    return dayOfWeek;
  }

  /**
   * @param dayOfWeek dayOfWeek or {@code null} for none
   */
  public EmpDayInfo setDayOfWeek(java.lang.Integer dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<EmpHourInfo> getHourInfo() {
    return hourInfo;
  }

  /**
   * @param hourInfo hourInfo or {@code null} for none
   */
  public EmpDayInfo setHourInfo(java.util.List<EmpHourInfo> hourInfo) {
    this.hourInfo = hourInfo;
    return this;
  }

  @Override
  public EmpDayInfo set(String fieldName, Object value) {
    return (EmpDayInfo) super.set(fieldName, value);
  }

  @Override
  public EmpDayInfo clone() {
    return (EmpDayInfo) super.clone();
  }

}
