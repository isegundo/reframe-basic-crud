(ns reframe-basic-crud.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com :refer [at]]
   [reframe-basic-crud.events :as events]
   [reframe-basic-crud.routes :as routes]
   [reframe-basic-crud.subs :as subs]
   [reframe-basic-crud.home.views]
   [reframe-basic-crud.tasks.views]))

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

(defn main-panel []
  (let [app-name (re-frame/subscribe [::subs/app-name])
        active-panel (re-frame/subscribe [::subs/active-panel])]
    [:div
     [header @app-name]
     [re-com/v-box
      :src      (at)
      :height   "100%"
      :children [(routes/panels @active-panel)]]]))
