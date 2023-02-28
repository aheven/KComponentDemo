package heven.holt.kcomponent.user.spi

import com.xiaojinzi.component.anno.ServiceAnno
import heven.holt.kcomponent.base.spi.UserSpi

@ServiceAnno(UserSpi::class)
class UserSpiImpl : UserSpi {
    override fun test(): String = "Hello World"
}