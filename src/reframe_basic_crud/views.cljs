(ns reframe-basic-crud.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com :refer [at]]
   [reframe-basic-crud.events :as events]
   [reframe-basic-crud.routes :as routes]
   [reframe-basic-crud.subs :as subs]))

;; home
(defn home-title []
  [re-com/title
   :src   (at)
   :label (str "This is the Home Page.")
   :level :level1])

(defn home-panel []
  [re-com/v-box
   :src      (at)
   :gap      "1em"
   :children [[home-title]]])

(defmethod routes/panels :home-panel [] [home-panel])

;; tasks
(defn tasks-title []
  [re-com/title
   :src   (at)
   :label "This is the Tasks Page."
   :level :level1])

(defn tasks-panel []
  [re-com/v-box
   :src      (at)
   :gap      "1em"
   :children [[tasks-title]]])

(defmethod routes/panels :tasks-panel [] [tasks-panel])

(defn link-to-home-page []
  [re-com/hyperlink
   :src      (at)
   :label    "Home"
   :on-click #(re-frame/dispatch [::events/navigate :home])])

(defn link-to-tasks-page []
  [re-com/hyperlink
   :src      (at)
   :label    "Tasks"
   :on-click #(re-frame/dispatch [::events/navigate :tasks])])

(defn header [app-name]
  [:div 
   [:h1 app-name]
   [link-to-home-page]
   [:br]
   [link-to-tasks-page]])

;; main

(defn main-panel []
  (let [app-name (re-frame/subscribe [::subs/app-name])
        active-panel (re-frame/subscribe [::subs/active-panel])]
    [:div 
     [header @app-name]
     [re-com/v-box
      :src      (at)
      :height   "100%"
      :children [(routes/panels @active-panel)]]]))
