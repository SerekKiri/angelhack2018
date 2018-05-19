import { ApolloLink } from 'apollo-link'
import { HttpLink } from 'apollo-link-http'
import { InMemoryCache } from 'apollo-cache-inmemory'
import { getCookie } from '../../lib/cookieUtils'
export default ctx => {
  const httpLink = new HttpLink({
    uri: 'http://10.1.1.222:4000', // Server URL (must be absolute)
    credentials: 'same-origin' // Additional fetch() options like `credentials` or `headers`
  })

  // middleware
  const middlewareLink = new ApolloLink((operation, forward) => {
    // This function is called before every request. Update ctx.req.session and window.__NUXT__.state.session
    // To point to wherever you store your token
    /*  const token = process.server
      ? ctx.req.session
      : window.__NUXT__.state.session;
*/
    operation.setContext({
      headers: {
        authorization: `Bearer ${getCookie(
          'token',
          process.server ? ctx.req.headers.cookie : document.cookie
        )}`
      }
    })
    return forward(operation)
  })
  const link = middlewareLink.concat(httpLink)
  return {
    link,
    cache: new InMemoryCache()
  }
}
