package idv.maxence.idv.maxence2997.socialmediaapp.config

import idv.maxence.idv.maxence2997.socialmediaapp.business.AuthBusinessService
import idv.maxence.idv.maxence2997.socialmediaapp.business.impl.AuthBusinessServiceImpl
import idv.maxence.idv.maxence2997.socialmediaapp.dao.repository.UserRepository
import idv.maxence.idv.maxence2997.socialmediaapp.dao.repository.UserRepositoryImpl
import org.koin.dsl.module

//TODO: rename to UserModule or AuthModule
val appModule = module {
  single<UserRepository> { UserRepositoryImpl() }
  // Use get() to retrieve the required instances in constructor
  single<AuthBusinessService> { AuthBusinessServiceImpl(get()) }
}