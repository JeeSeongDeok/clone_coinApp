package com.plcoding.cryptocurrencyappyt.domain.use_case.get_coins

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoin
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
){
    /*
     *  operator invoke: 이 함수를 불러낼 때 객체.invoke()를 하지 않아도 객체()로 호출 가능하다
     */
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow{
        try{
            // 로딩 중 작업표시줄을 띄우기 위해서
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "예상하지 못한 에러"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("인터넷 연결 실패"))
        }
    }
}