**Blog Post Articles Service**, like [medium.com](http://medium.com) 

Blog User **creates** articles and **publishes** them

Blog User can **login/logout**

**Permission mechanism** will describe: who can view articles, block them, see articles.
## Posts API

| URL | Method | Request | Response | Description | Description after Spring Security |
| --- | --- | --- | --- | --- | --- |
| /posts | GET |  | blog id
blog title | Search last posts, order by createdOn desc

Shows only posts with status PUBLISHED |  |
| /posts/create | POST | - title
- body
- tags []
- authorId | - blog id
- blog title
- blog body
- tags []
- blog status
- authorId |  |  |
| /posts/search | POST | - title
- author’s first name
- author’s last name
- published from
- published to
- tags [] | [
blog id, 
blog title
] | Search posts by search request

Shows only posts with status PUBLISHED |  |
| /posts/:id | GET |  | id
title
body
tags []
author
blog status
updated date
created date
 | show the entire blog article

Shows posts with any status |  |
| /posts/:id | PATCH | - blog title
- blog body
- tags [] |  | Updating the existing article |  |
| /posts/:id/publish | PUT |  |  | Sets blog status to PUBLISHED |  |
| /posts/:id/unpublish | PUT |  |  | Sets blog status to UNPUBLISHED |  |
| /posts/:id/block | PUT |  |  | Sets blog status to BLOCKED |  |
| /posts/:username | GET |  | [
- blog id
- blog title
] | Shows blog articles by a specific user

Shows only posts with status PUBLISHED |  |
| /posts/:id | DELETE |  |  | Delete the post |  |

## Author API

| URL | Method | Request | Response | Description | Description after Spring Security |
| --- | --- | --- | --- | --- | --- |
| /authors | GET |  | [
- author id
- author first name
- author last name
- author username
- blogs count
] | Shows authors, order by articles count (desc)

Count articles with status PUBLISHED only

If there are 6 articles: 
- 2 published
- 1 unpublished
- 3 blocked

Total articles: 2 |  |
| /authors/:id | GET |  | - author id
- author first name
- author last name
- author username
- blogs [
   blog id
   blog title
] | Shows author and their articles (order by created date desc)

Show articles with status PUBLISHED only

If there are 6 articles: 
- 2 published
- 1 unpublished
- 3 blocked

Show only: 2 published |  |

## Entry API

| URL | Method | Request | Response | Description | Description after Spring Security |
| --- | --- | --- | --- | --- | --- |
| /entry/registration | POST | - first name
- last name
- username
- password |  | Creates BlogUser  |  |
| /entry/login | POST | - username
- password | Bearer JWT token |  | Session token |
| /entry/logout | PUT

Authorization Header |  |  |  | Deprecate/Expire JWT token  |

# Stack

- Lombok
- Spring Web
- Spring Data JPA
- PostgreSQL
- Liquibase
- Spring Security 
(comment out all `security` dependencies in `pom.xml` before `**Spring Security**` session)
