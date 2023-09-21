import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import styles from "../css/write.module.scss"
import { useState } from 'react';

export default function Write() {
    const [text, setText] = useState({
        제목: '',
        내용: ''
    })
    const handleText = (e) => {
        const { name, value } = e.target;
        setText({ ...text, [name]: value })
    }
    console.log(text);
    const handleButton = () => {
        console.log('post')
    }
    return (
        <div className={styles.container}>
            <input type='text' onChange={handleText} name='제목' value={text.제목}></input>
            <CKEditor
                editor={ClassicEditor}
                data="<p></p>"
                onReady={editor => {
                }}
                onChange={(event, editor) => {
                    const data = editor.getData();
                    setText({
                        ...text,
                        내용: data
                    })
                    console.log(text);
                }}
                onBlur={(event, editor) => {
                }}
                onFocus={(event, editor) => {
                }}
            />
            <button onClick={handleButton}>등록</button>
        </div>
    )
}


