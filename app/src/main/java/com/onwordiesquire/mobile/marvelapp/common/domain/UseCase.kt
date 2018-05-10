package com.onwordiesquire.mobile.marvelapp.common.domain

/**
 * Created by wkda on 07.10.17.
 */
interface UseCase<in Q : UseCaseRequest, out R : UseCaseResponse<*>> {
    fun execute(request: Q): R
}