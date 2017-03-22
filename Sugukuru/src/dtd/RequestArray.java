/***************************
 * 学籍番号:40313
 * 作成者　:k.koki
 * 作成日　:2016/11/09
 * 内容　　:
 * *************************/
package dtd;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RequestArray {

}
