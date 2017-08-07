var gulp = require('gulp');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var eslint = require('gulp-eslint');


gulp.task("publish", function() {
  return gulp.src(['./*.js', '!./require.js', '!./gulpfile.js'])
    .pipe(eslint({
      "rules":{
          "quotes": ["error", "double"]
      },
      "globals":[
          "jQuery",
          "$",
          "eg"
      ]
    }))
    .pipe(eslint.format())
    .pipe(concat('reservation.min.js'))
    .pipe(uglify())
    .pipe(gulp.dest('./dist/'));
});
