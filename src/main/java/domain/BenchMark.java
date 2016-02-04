package domain;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by olexandra on 2/4/16.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface BenchMark {
    //should ignore this test?
    boolean countTime() default true;
    String createdBy() default "SashaUser";
}
