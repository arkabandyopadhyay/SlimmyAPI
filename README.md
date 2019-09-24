# SlimmyAPI
A Simple API for unified acess to GitHub and GitLab APIs.

for testing try host/api/{user}/{repoName}

NB: github access might be forbidden in later cases due to quote limit aginst an ip (solution : use some proxy in m/c to bypass it) and for now i have not used access token.

Some Silent Feature.

1> Basic Workflow model implemented so that configuration for api can be centralized.

2> GitHub and GitLab API for searching users and projects
Plan List for things to be done.

Going Ahead.

1> Async controller with exception handling @Advice and logging improvements.

2> On the fly customization using classloader.

3> Adhering to SOLID principle after refractering.

4> Pluggable API framework to be developed using runtime annotation thing.

5> Solving paging issu with rest api from github and gitlab i.e. by default it provides a page size of 30.


