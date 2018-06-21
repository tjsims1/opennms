module.exports = {
  // Destinations
  LIB_DEST: '/lib',
  OPENNMS_HOME: process.env.OPENNMS_HOME || '../target/opennms-2017.1.9-SNAPSHOT',
  JETTY_WEBAPP: '/jetty-webapps/opennms',

  // Sources.
  SASS_SOURCE: 'src/main/scss/**/*.scss',
  JSP_SOURCE: 'src/main/webapp/**/*.jsp',
  WEBAPP_SOURCE_BASE: 'src/main/webapp'
};
