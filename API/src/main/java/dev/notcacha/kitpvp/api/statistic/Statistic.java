package dev.notcacha.kitpvp.api.statistic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = DefaultStatistic.class)
public interface Statistic {

    /**
     * @return The total amount of statistic.
     */

    @JsonProperty("amount")
    int getAmount();

    /**
     * Add the amount of total amount.
     * @param amount has been added.
     */

    void add(int amount);

    /**
     * Remove the amount of total.
     * @param amount has been removed.
     */
    void remove(int amount);

    /**
     * Increase the amount of total statistic.
     *
     * @param amount has been increase.
     */

    default void increase(int amount) {
        add(getAmount() + 1);
    }
}
