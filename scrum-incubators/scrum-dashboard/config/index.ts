export default {
    development: {
        mode: 'dev',
        cdn: "./",
        mocked: true,
        port: 9000,
        apiBaseUrl: "http://localhost:3000",
        apiKey: "4chquagofh18sw1qhebgy4qk71hl6roqq6nobeyv8yu22n3g"
    },
    production: {
        mode: 'prod',
        cdn: "",
        mocked: false,
        apiBaseUrl: "http://localhost:3000"
    }
}