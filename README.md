# classname

####Current version:
[![Clojars Project](http://clojars.org/ajchemist/classname/latest-version.svg)](http://clojars.org/ajchemist/classname)

#### Usage
```clojure
(classname "a" 'b :c {:d true :x false} ["e" :f {'y nil "z" false :g []}])
;; => "a b c d e f g"
```
