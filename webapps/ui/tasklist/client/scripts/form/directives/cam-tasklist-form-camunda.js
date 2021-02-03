/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// /* eslint-disable */
'use strict';
const createForm = require('camunda-forms').createForm;

console.log('foo');

var angular = require('../../../../../../camunda-commons-ui/vendor/angular');
var $ = require('jquery');

module.exports = [
  'CamForm',
  'camAPI',
  '$timeout',
  '$translate',
  'Notifications',
  'Uri',
  'unfixDate',
  function(
    CamForm,
    camAPI,
    $timeout,
    $translate,
    Notifications,
    Uri,
    unfixDate
  ) {
    return {
      restrict: 'A',

      require: '^camTasklistForm',

      scope: true,

      template: '',

      link: function($scope, $element, attrs, formController) {
        formController.notifyFormInitialized();
        console.log($scope);
        console.log('element', $($element[0]).find('form')[0]);
        const data = {};

        let camForm = ($scope.camForm = null);
        var Task = camAPI.resource('task');

        $scope.variables = [];

        const loadVariables = function() {
          $scope.variablesLoaded = true;
          Task.formVariables(
            {
              id: formController.getParams().taskId,
              deserializeValues: false
            },
            function(err, result) {
              if (err) {
                $scope.variablesLoaded = false;
                return $translate('LOAD_VARIABLES_FAILURE')
                  .then(function(translated) {
                    Notifications.addError({
                      status: translated,
                      message: err.message,
                      scope: $scope
                    });
                  })
                  .catch(angular.noop);
              }
              angular.forEach(result, function(value, name) {
                var parsedValue = value.value;

                if (value.type === 'Date') {
                  parsedValue = unfixDate(parsedValue);
                }
                $scope.variables.push({
                  name: name,
                  value: parsedValue,
                  type: value.type,
                  fixedName: true
                });

                if (value.type === 'Object') {
                  $scope.variables.push({
                    name: name,
                    value: value.value,
                    type: value.type,
                    valueInfo: value.valueInfo
                  });
                }

                if (value.type === 'File') {
                  $scope.variables.push({
                    name: name,
                    type: value.type,
                    downloadUrl: Uri.appUri(
                      'engine://engine/:engine/task/' +
                        formController.getParams().taskId +
                        '/variables/' +
                        name +
                        '/data'
                    ),
                    readonly: true
                  });
                }
              });

              console.log($scope.variables);
            }
          );
        };

        function initializeVariables() {
          let params = formController.getParams();
          params = angular.copy(params);

          delete params.processDefinitionKey;

          angular.extend(params, {
            client: camAPI,
            formElement: document.createElement('form'),
            done: () => {
              console.log('done');
            }
          });

          $scope.camForm = camForm = new CamForm(params);
          camForm.variableManager;
          loadVariables();
        }

        function renderForm(schema) {
          console.log(
            camForm,
            camForm.variableManager,
            camForm.variableManager
          );
          const form = createForm({
            container: $element[0],
            schema,
            data
          });

          form.on('submit', event => {
            console.log('Form <submit>', event);
          });
        }

        function handleAsynchronousFormKey(formInfo) {
          console.log(formInfo);

          fetch(formInfo.key).then(async res => {
            const json = await res.json();

            initializeVariables();
            renderForm(json);
          });

          // asynchronousFormKey = formInfo;
          // if (formInfo && formInfo.loaded) {
          //   showForm(container, formInfo, formController.getParams());
          // }
          // if (formInfo && formInfo.failure) {
          //   formController.notifyFormInitializationFailed(formInfo.error);
          // }
        }

        $scope.$watch('asynchronousFormKey', handleAsynchronousFormKey, true);
      }
    };
  }
];
