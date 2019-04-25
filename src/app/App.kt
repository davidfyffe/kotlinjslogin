package app

import axios.Axios
import react.*
import react.dom.*
import logo.*
import ticker.*

interface AppState : RState {
    var loginResponse: String
}

class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        setState {
            loginResponse = ""
        }
    }



    override fun RBuilder.render() {

        div {

            div("App-header") {
                logo()
                h2 {
                    +"Welcome to React with Kotlin \n Login demo"
                }
            }
            p("App-intro") {
                +"Enter username and password "
                code { +"Admin ????" }
                +" and click login."
            }

            div("Login form") {
                login(::tryLogin)
                loginResults(state.loginResponse)
            }
        }
    }

    private fun tryLogin(username : String, password : String) {
        console.log("user $username, pass $password")


        val result = Axios.get<String>("http://localhost:8008/login?username=$username&password=$password").then {
            result -> console.log(result.headers) ; Pair(result.headers, result.data)
        }.catch {
            Pair("login failed", it.toString())
        }

        result.then {
            setState {
                loginResponse = it.toString()
            }
        }

    }

}

fun RBuilder.app() = child(App::class) {}




