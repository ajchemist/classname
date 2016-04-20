(set-env!
 :resource-paths #{"src"}
 :dependencies '[[org.clojure/clojure "1.8.0" :scope "provided"]
                 [org.clojure/clojurescript "1.7.122" :scope "provided"]

                 [adzerk/bootlaces   "0.1.13" :scope "test"]])

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.2.1")

(task-options!
 pom {:project 'ajchemist/classname
      :version +version+
      :description "A simple cljs utility for conditionally joining classNames together"
      :url "https://github.com/aJchemist/classname"
      :scm {:url "https://github.com/aJchemist/classname"}
      :license {"Eclipse Public License - v 1.0" "http://www.eclipse.org/legal/epl-v10.html"}}
 push {:repo "deploy-clojars"})
