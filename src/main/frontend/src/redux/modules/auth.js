import { combineActions, createAction, handleActions } from 'redux-actions'
import { fetchRequest, postRequest, Resource } from '../../api'

const signupSuccess = createAction('demo/auth/signup_success')
const signupError = createAction('/demo/auth/signup_error')

const signinSuccess = createAction('demo/auth/signin_success')
const signinError = createAction('demo/auth/signin_error')

export const signup = (name, username, email, password) => {
  return dispatch => {
    const request = postRequest(Resource.auth.signup, { name, username, email, password})

    request
      .then(response => dispatch(signupSuccess(response)))
      .catch(err => {
        console.error(err)
        dispatch(signupError(err))
      })

    return request
  }
}

export const signin = (usernameOrEmail, password) => {
  return dispatch => {
    const request = postRequest(Resource.auth.signin, {usernameOrEmail, password})

    request
      .then(response => dispatch(signinSuccess(response)))
      .catch(err => {
        console.error(err)
        dispatch(signinError(err))
      })

    return request
  }
}

const initialState = {
  message: null,
  error: null,
  status: null,
  
}

const signup = handleActions({
  [combineActions(signupSuccess, signinSuccess)]: (state, action) =>
})