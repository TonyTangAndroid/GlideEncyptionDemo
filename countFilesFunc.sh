#!/usr/bin/env bash

function count_files () {
    ls $1 | wc -l
}