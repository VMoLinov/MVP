package molinov.mvp.remote

import javax.inject.Inject

interface IApiHolder {

    val apiService: IDataSource
}

class ApiHolder @Inject constructor(
    override val apiService: IDataSource
) : IApiHolder
