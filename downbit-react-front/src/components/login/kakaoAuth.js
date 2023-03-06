const CLIENT_ID = 'bbc05f8f740b02532b51c83d8371464a'
const REDIRECT_AUTH_URI ='http://localhost:3000/user/kakao/callback'
const REDIRECT_LOGOUT_URI = 'http://localhost:3000/downbit'

export const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_AUTH_URI}&response_type=code`
export const KAKAO_LOGOUT_URL = `https://kauth.kakao.com/oauth/logout?client_id=${CLIENT_ID}&logout_redirect_uri=${REDIRECT_LOGOUT_URI}`