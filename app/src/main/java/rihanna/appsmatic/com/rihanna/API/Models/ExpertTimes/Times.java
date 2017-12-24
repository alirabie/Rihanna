package rihanna.appsmatic.com.rihanna.API.Models.ExpertTimes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eng Ali on 12/23/2017.
 */
public class Times {
    @SerializedName("2017-11-07T05:00:00")
    @Expose
    private String _20171107T050000;
    @SerializedName("2017-11-07T00:00:00")
    @Expose
    private String _20171107T000000;
    @SerializedName("2017-11-08T00:00:00")
    @Expose
    private String _20171108T000000;

    public String get20171107T050000() {
        return _20171107T050000;
    }

    public void set20171107T050000(String _20171107T050000) {
        this._20171107T050000 = _20171107T050000;
    }

    public String get20171107T000000() {
        return _20171107T000000;
    }

    public void set20171107T000000(String _20171107T000000) {
        this._20171107T000000 = _20171107T000000;
    }

    public String get20171108T000000() {
        return _20171108T000000;
    }

    public void set20171108T000000(String _20171108T000000) {
        this._20171108T000000 = _20171108T000000;
    }
}
