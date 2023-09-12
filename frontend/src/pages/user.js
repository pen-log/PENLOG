import { useParams } from 'react-router-dom';
import data from '../dummy/data';
import Nav from '../components/nav'; // Nav 컴포넌트 import 추가

const User = () => {
    const { id } = useParams();
    const item = data.find(item => item.name === id);

    // 사용자 이름 추출
    const userName = item ? item.name : '';

    return (
        <div>
            {item.name}님 페이지입니다.
            {item.content}
        </div>
    );
};

export default User;
