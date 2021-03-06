package dev.notcacha.kitpvp.api.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.notcacha.kitpvp.api.model.Model;
import dev.notcacha.kitpvp.api.user.statistic.UserStatistic;

@JsonDeserialize(as = KitUser.class)
public interface User extends Model {

    /**
     * @return The username of this user.
     */

    @JsonProperty("username")
    String getUsername();

    /**
     * @return The statistics manager of this user.
     */

    @JsonProperty("statistic")
    UserStatistic getStatistics();

    /**
     * @return The language of this user.
     */

    @JsonProperty("language")
    String getLanguage();

    /**
     * Change the language of user.
     * @param language language has been set.
     */

    void setLanguage(String language);
}
