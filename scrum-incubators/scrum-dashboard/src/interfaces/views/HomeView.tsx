import React from "react";
import AppNavBar from "@/interfaces/components/AppNavBar";
import { Editor } from '@tinymce/tinymce-react';
import requests from '@/infrastructure/utils/http';


function HomeView() {
	const editorRef = React.useRef(null);
	const [journals, setJournals] = React.useState("");
	React.useEffect(() => {
		requests.get({url: '/api/v1/journal'}).then(resp => {
			setJournals(JSON.stringify(resp));
		})
	}, []);
	return (
		<React.Fragment>
			<AppNavBar />
			<Editor
				onInit={(evt: any, editor: any) => editorRef.current = editor}
				apiKey={process.env.apiKey}
				initialValue={journals}
				init={{
					language: 'zh_CN',
					min_height: 500,
					width: 980,
					plugins: [
						'advlist', 'autolink', 'lists', 'link', 'image', 'charmap',
						'anchor', 'searchreplace', 'visualblocks', 'code', 'fullscreen',
						'insertdatetime', 'media', 'table', 'preview', 'help', 'wordcount'
					],
					toolbar: 'undo redo | blocks | ' +
						'bold italic forecolor | alignleft aligncenter ' +
						'alignright alignjustify | bullist numlist outdent indent | ' +
						'removeformat | help',
					content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }'
				}}
			/>
		</React.Fragment>
	)
}

export default HomeView;