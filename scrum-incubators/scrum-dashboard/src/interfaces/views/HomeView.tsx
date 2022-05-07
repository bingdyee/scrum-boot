import React from "react";
import AppNavBar from "@/interfaces/components/AppNavBar";
import { Editor } from '@tinymce/tinymce-react';


function HomeView() {
	const editorRef = React.useRef(null);
	return (
		<React.Fragment>
			<AppNavBar />
			<Editor
				onInit={(evt: any, editor: any) => editorRef.current = editor}
				apiKey={process.env.apiKey}
				init={{
					height: 500,
					menubar: false,
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