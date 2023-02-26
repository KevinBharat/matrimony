package my.matrimony.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 5/4/2018.
 */

public class Bean_State {

    @SerializedName("IsResult")
    @Expose
    private Integer isResult;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ResultList")
    @Expose
    private List<Bean_StateList> resultList = null;

    public Integer getIsResult() {
        return isResult;
    }

    public void setIsResult(Integer isResult) {
        this.isResult = isResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Bean_StateList> getResultList() {
        return resultList;
    }

    public void setResultList(List<Bean_StateList> resultList) {
        this.resultList = resultList;
    }
}
