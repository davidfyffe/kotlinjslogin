package app

import kotlinx.html.BUTTON
import kotlinx.html.InputType
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.*
import react.dom.button
import react.dom.div
import react.dom.h3
import react.dom.input


interface LoginProps : RProps {
    var onPasswordChange: (String) -> Any
    var onUsernameChange: (String) -> Any
    var onLoginClick : (String, String) -> Unit
}

interface LoginState : RState {
    var username : String
    var password : String
}


class Login(props : LoginProps) : RComponent<LoginProps, LoginState>(props) {

    override fun LoginState.init(props : LoginProps) {
        username = ""
        password = ""
    }

    fun onUserInputChange(event: Event) {
        val target = event.target as HTMLInputElement
        val uname = target.value
        setState {
            username = uname
        }
        //this.props.onUsernameChange(uname)
    }


    fun onPasswordInputChange(event: Event) {
        val target = event.target as HTMLInputElement
        val pass = target.value
        setState {
            password = pass
        }
        //this.props.onPasswordChange(pass)
    }

    override fun RBuilder.render() {

        div {
            div {
                +"Login form"
            }
            div {
                input(InputType.text) {
                    attrs {
                        value = state.username
                        onChangeFunction = ::onUserInputChange
                        placeholder = "username"
                    }
                }
            }
            div {
                input(InputType.password) {
                    attrs {
                        value = state.password
                        onChangeFunction = ::onPasswordInputChange
                        placeholder = "password"
                    }
                }
            }

            button {
                +"login"
                attrs.onClickFunction = ::onInputChange
            }
        }
    }

    private fun onInputChange(event: Event) {
//        val target = event.target as HTMLInputElement
//        val searchTerm = target.value
//        setState {
//            term = searchTerm
//        }
        this.props.onLoginClick(state.username, state.password)
    }


}

fun RBuilder.login(onLoginClick :  (String, String) -> Unit) = child(Login::class) {
    attrs.onLoginClick = onLoginClick
//    attrs.onUsernameChange = onUsernameChange
//    attrs.onPasswordChange = onPasswordChange
}


fun RBuilder.loginResults(loginResponse: String) {
    h3 {
        +"loginResponse $loginResponse"
    }
}