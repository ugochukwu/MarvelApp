package com.onwordiesquire.mobile.marvelapp.common.domain

/**
 * Created by wkda on 07.10.17.
 */
sealed class UseCaseResponse<P>

class FailureResponse<P>(val error: String) : UseCaseResponse<P>()
class SuccessResponse<P>(val payload: P) : UseCaseResponse<P>()