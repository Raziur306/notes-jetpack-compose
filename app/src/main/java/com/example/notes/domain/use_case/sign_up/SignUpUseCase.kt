package com.example.notes.domain.use_case.sign_up

import com.example.notes.common.NetworkResponse
import com.example.notes.data.remote.dto.RegisterDto
import com.example.notes.data.remote.dto.ResponseDto
import com.example.notes.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(registerDto: RegisterDto): Flow<NetworkResponse<ResponseDto>> = flow {
        try {
            emit(NetworkResponse.Loading())
            val response = authRepository.signUp(registerDto)

            if (response!!.isSuccessful && response.body() != null) {

                emit(NetworkResponse.Success(data = response.body()))

            } else if (response.errorBody() != null) {

                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                emit(NetworkResponse.Error(errorObj.getString("message")))

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