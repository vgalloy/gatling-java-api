package com.vgalloy.gatlingjavaapi.api.dsl.core;

import java.util.concurrent.TimeUnit;

import io.gatling.core.Predef;
import io.gatling.core.controller.inject.InjectionStep;
import scala.concurrent.duration.FiniteDuration;

/**
 * @author Vincent Galloy.
 *         Created by Vincent Galloy on 27/02/2017.
 */
public final class JavaInjectionSupport {

    /**
     * Constructor.
     * To prevent instantiation
     */
    private JavaInjectionSupport() {
        throw new AssertionError();
    }

    public static InjectionStep atOnceUsers(int i) {
        return Predef.atOnceUsers(i);
    }

    public static InjectionStep rampUsers(int users, long length, TimeUnit unit) {
        FiniteDuration finiteDuration = FiniteDuration.apply(length, unit);
        return Predef.rampUsers(users).over(finiteDuration);
    }

    public static InjectionStep nothingFor(long length, TimeUnit unit) {
        FiniteDuration finiteDuration = FiniteDuration.apply(length, unit);
        return Predef.nothingFor(finiteDuration);
    }

    public static InjectionStep constantUsersPerSec(double users, long length, TimeUnit unit) {
        FiniteDuration finiteDuration = FiniteDuration.apply(length, unit);
        return Predef.constantUsersPerSec(users).during(finiteDuration);
    }

    public static InjectionStep rampUsersPerSec(double rate1, double rate2, long length, TimeUnit unit) {
        FiniteDuration finiteDuration = FiniteDuration.apply(length, unit);
        return Predef.rampUsersPerSec(rate1).to(rate2).during(finiteDuration);
    }

    public static InjectionStep splitUsers(int user, InjectionStep step, long length, TimeUnit unit) {
        FiniteDuration finiteDuration = FiniteDuration.apply(length, unit);
        return Predef.splitUsers(user).into(step).separatedBy(finiteDuration);
    }

    public static InjectionStep splitUsers(int user, InjectionStep step, InjectionStep separator) {
        return Predef.splitUsers(user).into(step).separatedBy(separator);
    }

    public static InjectionStep heavisideUsers(int user, long length, TimeUnit unit) {
        FiniteDuration finiteDuration = FiniteDuration.apply(length, unit);
        return Predef.heavisideUsers(user).over(finiteDuration);
    }
}
