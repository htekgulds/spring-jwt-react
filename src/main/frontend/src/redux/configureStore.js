import { applyMiddleware, combineReducers, createStore } from 'redux'
import { createLogger } from 'redux-logger'
import thunkMiddleware from 'redux-thunk'

import UserReducer from './modules/user'

const loggerMiddleware = createLogger()

const middlewares = [
  thunkMiddleware
]

if (process.env.NODE_ENV !== 'production') {
  middlewares.push(loggerMiddleware)
}

const createStoreWithMiddleware = applyMiddleware(...middlewares)(createStore)

const reducers = combineReducers({
  user: UserReducer
})

const configureStore = initialState => createStoreWithMiddleware(reducers, initialState)
export default configureStore