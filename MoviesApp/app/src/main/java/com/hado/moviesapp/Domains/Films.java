
package com.hado.moviesapp.Domains;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Films {

    @SerializedName("data")
    @Expose
    private List<Film> data;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    public List<Film> getData() {
        return data;
    }

    public void setData(List<Film> data) {
        this.data = data;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
