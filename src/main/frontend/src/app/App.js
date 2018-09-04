import React, { Component } from 'react';
import { connect } from 'react-redux'
import { checkEmailAvailability, checkUsernameAvailability } from '../redux/modules/user'
import styled from 'styled-components'

const Container = styled.div`
  padding: 40px;
`

class App extends Component {
  render() {
    return (
      <Container>
      </Container>
    );
  }
}

const mapStateToProps = state => ({
  usernameAvailable: state.user.username,
  emailAvailable: state.user.email
})

export default connect(mapStateToProps, { checkUsernameAvailability, checkEmailAvailability })(App);
