package com.example.notes.domain.use_case.sign_in

import com.example.notes.common.NetworkResponse
import com.example.notes.data.remote.dto.SignInDto
import com.example.notes.domain.model.AuthModel
import com.example.notes.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(signInDto: SignInDto): Flow<NetworkResponse<AuthModel>> = flow {
        try {
            emit(NetworkResponse.Loading())
            val response = authRepository.signIn(signInDto)

            if (response!!.isSuccessful && response.body() != null) {

                emit(NetworkResponse.Success(data = response.body()))

            } else if (response.errorBody() != null) {

                try {
                    val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                    emit(NetworkResponse.Error(jsonObj.getString("message").toString()))
                } catch (e: Exception) {
                    emit(NetworkResponse.Error("Something went wrong."))
                }

            } else {
                emit(NetworkResponse.Error("Something went wrong"))
            }
        } catch (e: HttpException) {
            emit(NetworkResponse.Error(e.localizedMessage ?: "An unexpected error occurred."))

        } catch (e: IOException) {
            emit(NetworkResponse.Error("Check your internet connection"))
        }
    }
}