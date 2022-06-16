# Requirements

## Vision

StackFellows is designed as a community site for Code Fellows students and alumni to ask questions, get answers, or
otherwise find support during and after their Code Fellows journey.

The site allows users to post questions, write answers and provide support to other users, and allow supporting
answers to other users posts.

## Scope

### In-Scope

- A new user to the site will be able to *register* to become a site user.
- A registered user will be able to logon to the website using a custom username and password.
- A registered, logged in user will be able to view their own Profile.
- A registered, logged in user will be able to post a question / blog to the site, and view other registered users posts and "up votes".
- A registered, logged in user can "up vote" another registered users posts.
- 
### Out of Scope

What our product will *not* do:

- Auto correct (spelling or grammar)
- Validate code
- Guarantee security of your password or identity
- Have a dynamic user interface for every device type i.e. smartphones and smaller tables might have ui issues

## Minimum Viable Product

- A web app that enables user to self-register and logon.
- Web app uses a relational database backend.
- Authentication is handled using Spring Security.
- Users can Post, Up-Vote, and Comment.
- At least 2 Entity Models used to track database data.
- A RESTful API is implemented.
- A users post is associated with the user that created it.
- User can share code in their discussion / posts.

### Stretch Goals

- User can "private message" their instructor within the webapp.
- A quick Search utilize allows searching for posts and comments.
- Users can "connect with other Code Fellows alumni".
- Registered users can upload images (i.e. Profile; Screen shot).
- Inspirational quotes will be shown on-screen while a user is logged on.
- Limits will be placed on authorization (i.e. certain users can do certain things, others can not).
- An Administrative user is able to add/remove rights to registered users.

## Functional Requirements

- A website visitor can self-register with their own username and password.
- A registered user can log on to the website.
- Registered, logged on users can make posts, comment on them, and "up vote" other users posts.
- Registered users can see each other's posts and "up votes".

## Dependencies

- Relational Database system such as PostgresQL.
- Spring JPA and JPA Repository.
- Spring MVC.
- Spring Authentication Manager.
- Spring Password Encoder.
- Javax Persistence.
- Spring Security 5.
- Gradle 2.7.x.
- Spring MVC Hiddenmethod filter.

## Application Properties

We recommend disabling Whitelabel errors in application.properties with `werver.error.whitelabel.enabled=false`
