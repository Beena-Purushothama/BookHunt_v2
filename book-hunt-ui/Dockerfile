FROM node

#set working directory 
RUN mkdir /usr/src/app
#copy all files from current directory to docker
COPY . /usr/src/app

WORKDIR /usr/src/app
ENV PATH /usr/src/app/node_modules/.bin:$PATH

RUN yarn 

#start app
CMD ["npm", "start"]