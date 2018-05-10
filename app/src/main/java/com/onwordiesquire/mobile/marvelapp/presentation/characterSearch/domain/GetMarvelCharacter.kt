package com.onwordiesquire.mobile.marvelapp.presentation.characterSearch.domain

import com.onwordiesquire.mobile.marvelapp.common.domain.UseCase
import com.onwordiesquire.mobile.marvelapp.common.domain.UseCaseRequest
import com.onwordiesquire.mobile.marvelapp.common.domain.UseCaseResponse
import com.onwordiesquire.mobile.marvelapp.data.model.MarvelCharacter

/**
 * Created by wkda on 07.10.17.
 */
class GetMarvelCharacter : UseCase<Request, UseCaseResponse<MarvelCharacter>> {
    override fun execute(request: Request): UseCaseResponse<MarvelCharacter> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class Request : UseCaseRequest()