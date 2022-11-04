Run *mvn package* with the result:


    Given Docker name 'registry.gitlab.com/myproject/myrepo/mycontainer:${git.commit.id.abbrev}' is invalid:
       * tag part '${git.commit.id.abbrev}' doesn't match allowed pattern '^[\w][\w.-]{0,127}$'
    See http://bit.ly/docker_image_fmt for more details
