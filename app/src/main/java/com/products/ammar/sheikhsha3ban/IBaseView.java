package com.products.ammar.sheikhsha3ban;

/**
 * Created by AmmarRabie on 08/03/2018.
 */

public interface IBaseView<T extends IBaseActions> {

    void setPresenter(T presenter);

}