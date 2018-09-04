import Axios from 'axios'
import { ACCESS_TOKEN, API_BASE_URL } from './constants'

export const Resource = {
  auth: {
    signup: API_BASE_URL + '/auth/signup',
    signin: API_BASE_URL + '/auth/signin'
  },
  user: {
    me: API_BASE_URL + '/user/me',
    username_available: API_BASE_URL + '/user/checkUsernameAvailability',
    email_available: API_BASE_URL + '/user/checkEmailAvailability'
  },
  users: {
    profile (username) {
      return `${API_BASE_URL}/users/${username}`
    },
    polls (username) {
      return `${API_BASE_URL}/users/${username}/polls`
    },
    votes (username) {
      return `${API_BASE_URL}/users/${username}/votes`
    }
  },
  polls: {
    polls: API_BASE_URL + '/polls',
    poll (pollId) {
      return `${API_BASE_URL}/polls/${pollId}`
    },
    votes (pollId) {
      return `${API_BASE_URL}/polls/${pollId}/votes`
    }
  }
}

const getHeaders = () => {
  const headers = new Headers({
    'Content-Type': 'application/json'
  })

  if(localStorage.getItem(ACCESS_TOKEN)) {
    headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
  }

  return { headers }
}

export const fetchRequest = (url, params) =>
  Axios.get(
    url, {
      params: params || null,
      headers: getHeaders(),
      baseURL: API_BASE_URL
    })

export const postRequest = (url, body) =>
  Axios.post(
    url, body, {
      headers: getHeaders(),
      baseURL: API_BASE_URL
    })