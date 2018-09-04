import { createAction, handleAction } from 'redux-actions'
import { fetchRequest, Resource } from '../../api'
import { combineReducers } from 'redux'

const checkUsernameAvailable = createAction('demo/user/check_username_available')
const checkEmailAvailable = createAction('demo/user/check_email_available')


export const checkUsernameAvailability = username => {
  return dispatch => {
    const request = fetchRequest(Resource.user.username_available, {username})

    request
      .then(response => dispatch(checkUsernameAvailable(response)))
      .catch(err => {
        console.error(err)
        dispatch(checkUsernameAvailable(err))
      })

    return request
  }
}

export const checkEmailAvailability = email => {
  return dispatch => {
    const request = fetchRequest(Resource.user.email_available, { email })

    request
      .then(response => dispatch(checkEmailAvailable(response)))
      .catch(err => {
        console.error(err)
        dispatch(checkEmailAvailable(err))
      })

    return request
  }
}

const initialState = {
  username: null,
  email: null
}

const username = handleAction(
  checkUsernameAvailable,
  (state, action) => action.payload.data.available,
  initialState.username)

const email = handleAction(
  checkEmailAvailable,
  (state, action) => action.payload.data.available,
  initialState.email)

export default combineReducers({ username, email })
