package controllers.admin

import com.google.inject.Inject
import controllers.AuthConfigImpl
import dao.UserDAO
import jp.t2v.lab.play2.auth.AuthElement
import model.Role._
import model.form.data.LoginFormData
import play.api.Environment
import play.api.i18n.I18nSupport
import play.api.mvc.Controller

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author Camilo Sampedro <camilo.sampedro@udea.edu.co>
  */
abstract class ControllerWithAuthRequired @Inject()(implicit userDAO: UserDAO, override val cookieSecureOptionPlay: Environment) extends Controller with I18nSupport with AuthElement with AuthConfigImpl {
  implicit val isAdmin = true

  override def resolveUser(id: LoginFormData)(implicit context: ExecutionContext): Future[Option[User]] = userDAO.get(id)

  def AuthRequiredAction = AsyncStack(AuthorityKey -> Administrator)(_)
}
