import { BsXLg, BsGithub, BsGoogle, BsFacebook } from "react-icons/bs";
function Login() {
    return (
        <div className="login-container">
            <div className="login-left">
                <div className="login-img">
                    <img src="https://static.velog.io/static/media/undraw_joyride_hnno.fae6b95e.svg"></img>
                </div>
            </div>
            <div className="login-right">
                <div className="login-exit">
                    <BsXLg />
                </div>
                <div className="login-content">
                    <h2>로그인</h2>
                    <div className="login-content-email">
                        <h4>이메일로 로그인</h4>
                        <form className="login-form">
                            <input type="email" placeholder="이메일을 입력하세요." />
                            <button>로그인</button>
                        </form>
                    </div>
                    <div className="login-content-social">
                        <h4>소셜 계정으로 로그인</h4>
                        <div className="login-cotent-icon">
                            <div><BsGithub /></div>
                            <div><BsGoogle /></div>
                            <div><BsFacebook /></div>
                        </div>
                    </div>
                    <div className="login-content-footer">
                        <h4>아직 회원이 아니신가요?</h4>
                        <h4><span>회원가입</span></h4>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login
