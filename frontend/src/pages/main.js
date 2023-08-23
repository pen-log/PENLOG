import styles from '../css/main.module.scss';

function Main() {
    const 상품 = ['타입스크립트 때려잡는 꿀팁 모음dddasdasdd', '제목', '여기제목', '저기제목', '이런거저런거제목', 'ㅋㅋㅋ', '~~하는법']
    return (
        <div className={styles.main_container}>
            {상품.map((a, i) => (
                <div className={styles.main_item}>

                    <div className={styles.main_img}>
                        <img src="/PENLOG/frontend/public/logo.png" />
                    </div>

                    <div className={styles.main_info}>
                        <span>{a}</span>
                        <div className={styles.main_content}>
                            <p>본문내용본문 내용본문 내용본문 내용본내용타입스크립트 때려잡는 꿀팁 모음dddasdasdd타입스크립트 때려잡는 꿀팁 모음dddasdasdd타입스크립트 때려잡는 꿀팁 모음dddasdasdd타입스크립트 때려잡는 꿀팁 모음dddasdasdd</p>
                        </div>
                    </div>

                    <div className={styles.main_id}>
                        <h5>날짜</h5>
                        by : 내아이디~~ 좋아요수
                    </div>

                </div>
            ))
            }
        </div >
    )
}

export default Main
