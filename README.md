# About
Simple Java Spring application 

# Setup and installation

## Backend Prerequisites
- java **8** sdk (note: right now Spring 1.x does not support java 9. At the time of writing Spring Boot 2 is in pre-release and will support java 9)
        `brew tap caskroom/versions`
        `brew cask search java`
        `brew cask install java8`
- gradle
- postgres (`brew install postgres`)
- selelium chromedriver 


## Frontend Prerequisites
- Node 8.9 (`brew install node`)
- Yarn (`brew install yarn`)

## Setup
1. `git clone <project>`
1. configure postgres:
  - if running locally: start the postgres server, and create
    - for development : `createdb <name>`
    - for test: `createdb <name>`
1. install dependencies for the front-end
  - `cd client`
  - `yarn install`
1. build initial front-end for production
  - make an empty "public" directory (from root folder) `mkdir /server/src/main/resources/public`
  - `cd client && yarn build`
  - caveat: if running on Windows Machine
    - open /client/package.json
    - edit line: `"build": "react-scripts build && rm -rf ../server/src/main/resources/public && mv build ../server/src/main/resources/public"`
       1. change to `"build && rd /s /q \"../server/src/main/resources/public\" && move build \"../server/src/main/resources/public\"`
       3. save (*do not commit these changes to git... jenkins is linux!*)
1. build backend
  - `cd server`
      - `./gradle assemble`  (only builds jar file)
      - `./gradlew build` (will build artifacts and run tests)
        - *test require the Selelium Chrome Driver*
      - `./gradle bootrun`  (builds artifacts and runs development server *you must manually stop the server*)
  - or you can run these gradle processes through Eclipse or Intellij

- Note: install the `lombok` plugin in Eclipse & Intellij


# Usage

## Running locally

From the root directory:

1. cd server; ./gradlew bootRun
2. cd client; yarn start


## Running tests

- Run Back-end Tests Except Acceptance
cd server; ./gradlew test

- Run Front-end Tests
cd client; yarn test

